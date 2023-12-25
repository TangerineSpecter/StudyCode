package com.tangerine.specter.population.controller;

import com.tangerine.specter.common.ServiceResult;
import com.tangerine.specter.population.service.PopulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 人口增长演示
 *
 * @author tangerinespecter
 * @date 2023年06月13日12:46:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/population")
public class PopulationController {

    private final PopulationService populationService;

    @GetMapping("/index")
    public ModelAndView page(Model model) {
        model.addAttribute("worldInfo", populationService.v10Version());
        return ServiceResult.jumpPage("/population/index");
    }
}
