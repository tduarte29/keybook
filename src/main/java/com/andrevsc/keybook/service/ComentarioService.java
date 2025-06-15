// package com.andrevsc.keybook.service;

// import com.andrevsc.keybook.dto.comentario.ComentarioCreateDTO;
// import com.andrevsc.keybook.dto.comentario.ComentarioResponseDTO;
// import com.andrevsc.keybook.model.Comentario;
// import com.andrevsc.keybook.model.Item;
// import com.andrevsc.keybook.repository.ComentarioRepository;
// import com.andrevsc.keybook.repository.ItemRepository;
// import jakarta.persistence.EntityNotFoundException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class ComentarioService {

//     @Autowired
//     private ComentarioRepository comentarioRepository;

//     @Autowired
//     private ItemRepository itemRepository;

//     public ComentarioResponseDTO createComentario(ComentarioCreateDTO dto, Long itemId) {
//         Item item = itemRepository.findById(itemId)
//                 .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com o id: " + itemId));

//         Comentario novoComentario = new Comentario();
//         novoComentario.setTexto(dto.texto());
//         novoComentario.setItem(item);

//         Comentario savedComentario = comentarioRepository.save(novoComentario);
//         return new ComentarioResponseDTO(savedComentario);
//     }

//     public List<ComentarioResponseDTO> getComentariosByItemId(Long itemId) {
//         if (!itemRepository.existsById(itemId)) {
//             throw new EntityNotFoundException("Item não encontrado com o id: " + itemId);
//         }
//         return comentarioRepository.findByItemId(itemId)
//                 .stream()
//                 .map(ComentarioResponseDTO::new)
//                 .collect(Collectors.toList());
//     }
    
//     @Transactional
//     public ComentarioResponseDTO updateComentario(Long comentarioId, ComentarioCreateDTO dto) {
//         Comentario comentarioExistente = comentarioRepository.findById(comentarioId)
//                 .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado com o id: " + comentarioId));
    
//         comentarioExistente.setTexto(dto.texto());
    
//         Comentario updatedComentario = comentarioRepository.save(comentarioExistente);
//         return new ComentarioResponseDTO(updatedComentario);
//     }

//     public void deleteComentario(Long comentarioId) {
//         if (!comentarioRepository.existsById(comentarioId)) {
//             throw new EntityNotFoundException("Comentário não encontrado com o id: " + comentarioId);
//         }
//         comentarioRepository.deleteById(comentarioId);
//     }
// }