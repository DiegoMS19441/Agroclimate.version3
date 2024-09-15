package br.com.fiap.agroclimate.controller;

import br.com.fiap.agroclimate.dto.colheita.AtualizarColheitaDto;
import br.com.fiap.agroclimate.dto.colheita.CadastrarColheitaDto;
import br.com.fiap.agroclimate.dto.colheita.InformacaoColheitaDto;
import br.com.fiap.agroclimate.dto.colheita.ListagemColheitaDto;
import br.com.fiap.agroclimate.dto.usuario.agricultor.DetalhesAgricultorDto;
import br.com.fiap.agroclimate.model.Colheita;
import br.com.fiap.agroclimate.repository.ColheitaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("colheitas")
@Tag(name = "Colheita", description = "Gerenciamento de colheitas")
public class ColheitaController {

    @Autowired
    private ColheitaRepository colheitaRepository;

    @PostMapping
    @Transactional
    @Operation(description = "Insere informações de uma colheita "
            , summary = "Cadastrar uma colheita")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Cadastro de colheita realizado!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoColheitaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<InformacaoColheitaDto> post(@RequestBody @Valid CadastrarColheitaDto colheitaDto, UriComponentsBuilder uriBuilder) {
        var colheita = new Colheita(colheitaDto);
        colheitaRepository.save(colheita);
        var uri = uriBuilder.path("/colheitas/{id}").buildAndExpand(colheita.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformacaoColheitaDto(colheita));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Operation(description = "Remove uma colheita "
            , summary = "Deletar uma colheita")
    @ApiResponses({
            @ApiResponse(responseCode = "no content", description = "Registro deletado!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoColheitaDto.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        colheitaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(description = "Atualizar os dados de uma colheita "
            , summary = "Atualizar uma colheita")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados atualizados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoColheitaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<InformacaoColheitaDto> put(@PathVariable("id") Long id,
                                                     @RequestBody AtualizarColheitaDto dto) {
        var colheita = colheitaRepository.getReferenceById(id);
        colheita.atualizarColheita(dto);
        return ResponseEntity.ok(new InformacaoColheitaDto(colheita));
    }

    @GetMapping("{id}")
    @Transactional
    @Operation(description = "Recupera os dados de uma colheita "
            , summary = "Retorna uma colheita")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoColheitaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<InformacaoColheitaDto> get(@PathVariable("id") Long id) {
        var colheita = colheitaRepository.getReferenceById(id);
        return ResponseEntity.ok(new InformacaoColheitaDto(colheita));
    }

    @GetMapping
    @Operation(description = "Recuperação de dados paginados "
            , summary = "Retorna uma colheita| Paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "Ok", description = "Dados retornados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = InformacaoColheitaDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")}
    )
    public ResponseEntity<List<ListagemColheitaDto>> get(Pageable pageable) {
        var listaDto = colheitaRepository.findAll(pageable)
                .stream().map(ListagemColheitaDto::new).toList();
        return ResponseEntity.ok(listaDto);

    }

}

