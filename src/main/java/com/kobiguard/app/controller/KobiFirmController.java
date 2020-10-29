package com.kobiguard.app.controller;

import com.kobiguard.app.dto.KobiFirmDto;
import com.kobiguard.app.entity.KobiFirm;
import com.kobiguard.app.resources.KobiFirmResource;
import com.kobiguard.app.service.KobiFirmService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KobiFirmController extends BaseController {
    private final KobiFirmService kobiFirmService;
    private final ConversionService conversionService;

    public KobiFirmController(KobiFirmService kobiFirmService, ConversionService conversionService) {
        this.kobiFirmService = kobiFirmService;
        this.conversionService = conversionService;
    }

    @GetMapping("/kobifirms")
    public ResponseEntity<Page<KobiFirmResource>> getKobiFirms(@PageableDefault Pageable pageable) {
        Page<KobiFirm> kobiFirm = kobiFirmService.findAllKobiFirms(pageable);
        List<KobiFirmResource> kobiFirmResourceList = new ArrayList<>();
        kobiFirm.get().forEach(item -> kobiFirmResourceList.add(conversionService.convert(item, KobiFirmResource.class)));
        return ResponseEntity.ok(new PageImpl<>(kobiFirmResourceList, kobiFirm.getPageable(), kobiFirm.getTotalElements()));
    }

    @PostMapping("/kobifirm")
    public ResponseEntity<KobiFirmResource> createKobiFirm(@RequestBody @Valid KobiFirmDto kobiFirmDto) {
        return ResponseEntity.ok(conversionService.convert(kobiFirmService.createKobiFirm(conversionService.convert(kobiFirmDto, KobiFirm.class)),
                KobiFirmResource.class));
    }

    @GetMapping("/kobifirm/{kobiFirmId}")
    public ResponseEntity<KobiFirmResource> getKobiFirm(@PathVariable("kobiFirmId") String kobiFirmId) {
        return ResponseEntity.ok(conversionService.convert(kobiFirmService.findKobiFirmById(kobiFirmId), KobiFirmResource.class));
    }

}
