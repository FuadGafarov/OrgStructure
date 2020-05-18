package az.demo.orgstructure.rest.converter;

import az.demo.orgstructure.model.OrgStructure;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrgStructureConverter {
    public List<OrgStructure> convert(List<OrgStructure> orgStructure) {
        return orgStructure.stream().map(element -> {
            element.setSubStructures(
                    orgStructure.stream()
                            .filter(subElement -> subElement.getParent().equals(element.getId()))
                            .collect(Collectors.toList()));
            return element;
        }).collect(Collectors.toList());
    }
}
