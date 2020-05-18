package az.demo.orgstructure.repository;

import az.demo.orgstructure.model.OrgStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrgStructureRepository extends JpaRepository<OrgStructure, Long> {
    List<OrgStructure> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
