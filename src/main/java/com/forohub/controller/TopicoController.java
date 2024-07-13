package com.forohub.controller;

import com.forohub.Topic.DatosActualizacionTopico;
import com.forohub.Topic.DatosDetallesTopico;
import com.forohub.Topic.DatosListadoTopicos;
import com.forohub.Topic.DatosRegistroTopico;
import com.forohub.Topic.Topico;
import com.forohub.repositorio.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        if (topicoRepository.findByTituloOrMensaje(datosRegistroTopico.getTitulo(), datosRegistroTopico.getMensaje()).isEmpty()) {
            Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(topico);
        } else {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título o mensaje.");
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(
            @PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            DatosDetallesTopico detallesTopico = new DatosDetallesTopico(
                    topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),
                    topico.getStatus(), topico.getAutor(), topico.getCurso());
            return ResponseEntity.ok(detallesTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizacionTopico datosActualizacionTopico) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.setTitulo(datosActualizacionTopico.getTitulo());
            topico.setMensaje(datosActualizacionTopico.getMensaje());
            topico.setFecha(datosActualizacionTopico.getFecha());
            topico.setStatus(datosActualizacionTopico.getStatus());
            topico.setAutor(datosActualizacionTopico.getAutor());
            topico.setCurso(datosActualizacionTopico.getCurso());
            topicoRepository.save(topico);
            DatosDetallesTopico detallesTopico = new DatosDetallesTopico(
                    topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),
                    topico.getStatus(), topico.getAutor(), topico.getCurso());
            return ResponseEntity.ok(detallesTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
