package vg.mathias.delacruz.hackathon.project.service;

import org.springframework.stereotype.Service;
import vg.mathias.delacruz.hackathon.project.model.News;
import vg.mathias.delacruz.hackathon.project.repository.NewsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository repo;

    public NewsService(NewsRepository repo) {
        this.repo = repo;
    }

    public List<News> listAll() {
        return repo.findAll();
    }

    public Optional<News> getById(Integer id) {
        return repo.findById(id);
    }

    public List<News> listByStatus(Boolean active) {
        return repo.findByIsActive(active);
    }

    /** Crear noticia */
    public News create(News n) {
        n.setIsActive(true);
        n.setCreatedAt(LocalDateTime.now());
        n.setUpdatedAt(LocalDateTime.now());
        return repo.save(n);
    }

    /** Actualizar noticia */
    public News update(Integer id, News n) {
        return repo.findById(id).map(existing -> {

            existing.setName(n.getName());
            existing.setSurname(n.getSurname());
            existing.setTypeDoc(n.getTypeDoc());
            existing.setDocNumb(n.getDocNumb());
            existing.setPais(n.getPais());
            existing.setAddress(n.getAddress());
            existing.setDescription(n.getDescription());

            existing.setUpdatedAt(LocalDateTime.now());

            return repo.save(existing);

        }).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
    }

    /** Eliminación lógica */
    public void logicalDelete(Integer id) {
        repo.findById(id).ifPresent(n -> {
            n.setIsActive(false);
            n.setDeletedAt(LocalDateTime.now());
            n.setUpdatedAt(LocalDateTime.now());
            repo.save(n);
        });
    }

    /** Restauración */
    public void restore(Integer id) {
        repo.findById(id).ifPresent(n -> {
            n.setIsActive(true);
            n.setRestoredAt(LocalDateTime.now());
            n.setUpdatedAt(LocalDateTime.now());
            repo.save(n);
        });
    }
}
