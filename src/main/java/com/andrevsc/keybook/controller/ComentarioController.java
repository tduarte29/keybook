// package com.andrevsc.keybook.controller;

// import com.andrevsc.keybook.dto.comentario.ComentarioCreateDTO;
// import com.andrevsc.keybook.dto.comentario.ComentarioResponseDTO;
// import com.andrevsc.keybook.service.ComentarioService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping
// public class ComentarioController {

//     @Autowired
//     private ComentarioService comentarioService;

//     // ALTERADO: Usa os DTOs
//     @PostMapping("/items/{itemId}/comments")
//     public ResponseEntity<ComentarioResponseDTO> createComentario(@PathVariable Long itemId, @RequestBody ComentarioCreateDTO dto) {
//         ComentarioResponseDTO createdComentario = comentarioService.createComentario(dto, itemId);
//         return ResponseEntity.status(201).body(createdComentario);
//     }

//     // ALTERADO: Retorna lista de DTOs
//     @GetMapping("/items/{itemId}/comments")
//     public ResponseEntity<List<ComentarioResponseDTO>> getComentariosByItemId(@PathVariable Long itemId) {
//         List<ComentarioResponseDTO> comentarios = comentarioService.getComentariosByItemId(itemId);
//         return ResponseEntity.ok(comentarios);
//     }

//     // ALTERADO: Usa DTO no update
//     @PutMapping("/comments/{comentarioId}")
//     public ResponseEntity<ComentarioResponseDTO> updateComentario(@PathVariable Long comentarioId, @RequestBody ComentarioCreateDTO dto) {
//         ComentarioResponseDTO updatedComentario = comentarioService.updateComentario(comentarioId, dto);
//         return ResponseEntity.ok(updatedComentario);
//     }

//     @DeleteMapping("/comments/{comentarioId}")
//     public ResponseEntity<Void> deleteComentario(@PathVariable Long comentarioId) {
//         comentarioService.deleteComentario(comentarioId);
//         return ResponseEntity.noContent().build();
//     }
// }