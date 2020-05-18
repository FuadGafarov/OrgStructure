package az.demo.orgstructure.rest;

import az.demo.orgstructure.model.OrgStructure;
import az.demo.orgstructure.repository.OrgStructureRepository;
import az.demo.orgstructure.rest.converter.OrgStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orgStructure")
public class OrgStructureController {
    @Autowired
    private OrgStructureRepository orgStructureRepository;
    @Autowired
    private OrgStructureConverter converter;

    @GetMapping
    public List<OrgStructure> getOrgStructures(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return converter.convert(orgStructureRepository.findByDateBetween(startDate, endDate))
                .stream()
                .filter(element -> element.getParent() == 0)
                .collect(Collectors.toList());
    }

    @PostMapping
    public OrgStructure addNewOrgStructure(@Valid @RequestBody OrgStructure orgStructure) {
        return orgStructureRepository.save(orgStructure);
    }

    @DeleteMapping
    public void deleteOrgStructure(Long id) {
        orgStructureRepository.findById(id).ifPresent(orgStructureRepository::delete);
    }

    @PutMapping
    public OrgStructure editOrgStructure(@Valid @RequestBody OrgStructure orgStructure) {
        return orgStructureRepository.save(orgStructure);
    }
}
