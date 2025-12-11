package vg.mathias.delacruz.hackathon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vg.mathias.delacruz.hackathon.project.model.News;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByIsActive(Boolean active);
}
