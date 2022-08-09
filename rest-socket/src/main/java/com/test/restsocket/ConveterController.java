package com.test.restsocket;

import com.test.restsocket.domain.ConvetedMenssage;
import com.test.restsocket.domain.ConveterPayload;
import com.test.restsocket.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConveterController {
    @Autowired
    private ConverterService converterService;

    @PostMapping
    public ResponseEntity<ConvetedMenssage> converter(@RequestBody ConveterPayload body) {
        var cm = new ConvetedMenssage();
        cm.setDate(converterService.converter(body.getDate()));
        return ResponseEntity.ok().body(cm);
    }
}
