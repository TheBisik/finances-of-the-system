package pl.pcdevs.systemfinansowy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pcdevs.systemfinansowy.model.DataGenarate;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DataGenarateRepostiory extends JpaRepository<DataGenarate, Long> {
    Optional<DataGenarate> findById(int id);

}
