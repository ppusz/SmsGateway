package pusz.name.smsgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pusz.name.smsgateway.controller.exception.DistributionListNotFoundException;
import pusz.name.smsgateway.domain.DistributionListDto;
import pusz.name.smsgateway.mapper.DistributionListMapper;
import pusz.name.smsgateway.service.DbService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/distributionlist")
@CrossOrigin(origins = "*")
public class DistributionListController {

    @Autowired
    DistributionListMapper mapper;
    @Autowired
    DbService dbService;

    @GetMapping(value = "getAll")
    public List<DistributionListDto> getDistributionLists() {
        return mapper.mapToDistributionListDtoList(dbService.getAllDistributionLists());
    }

    @GetMapping(value = "getOne")
    public DistributionListDto getDistributionList(@RequestParam Long id) throws DistributionListNotFoundException {
        return mapper.mapToDistributionListDto(dbService.getDistributionList(id).orElseThrow(DistributionListNotFoundException::new));
    }

    @DeleteMapping(value = "delete")
    public void deleteDistributionList(@RequestParam Long id) {
        dbService.deleteDistributionList(id);
    }

    @PutMapping(value = "update", consumes = APPLICATION_JSON_VALUE)
    public DistributionListDto updateDistributionList(@RequestBody DistributionListDto distributionListDto) {
        return mapper.mapToDistributionListDto(dbService.saveDistributionList(mapper.mapToDistributionList(distributionListDto)));
    }

    @PostMapping(value = "create", consumes = APPLICATION_JSON_VALUE)
    public DistributionListDto createDistributionList(@RequestBody DistributionListDto distributionListDto) {
        return mapper.mapToDistributionListDto(dbService.saveDistributionList(mapper.mapToDistributionList(distributionListDto)));
    }
}
