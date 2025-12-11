package vg.mathias.delacruz.hackathon.project.rest;

import vg.mathias.delacruz.hackathon.project.model.News;
import vg.mathias.delacruz.hackathon.project.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/news")
@Tag(name = "News API", description = "API para gestión de noticias")
public class NewsRest {

    @Autowired
    private NewsService service;

    @GetMapping
    @Operation(summary = "Listzar todas las noticias")
    public List<News> findAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar noticia por ID")
    public Optional<News> findById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/state/{active}")
    @Operation(summary = "Listar noticias por estado")
    public List<News> findByState(@PathVariable Boolean active) {
        return service.listByStatus(active);
    }

    @PostMapping("/save")
    @Operation(summary = "Registrar noticia nueva")
    public News save(@RequestBody News news) {
        return service.create(news);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar noticia")
    public News update(@PathVariable Integer id, @RequestBody News news) {
        return service.update(id, news);
    }

    @PatchMapping("/delete/{id}")
    @Operation(summary = "Eliminar noticia (lógico)")
    public void delete(@PathVariable Integer id) {
        service.logicalDelete(id);
    }

    @PatchMapping("/restore/{id}")
    @Operation(summary = "Restaurar noticia")
    public void restore(@PathVariable Integer id) {
        service.restore(id);
    }
}
