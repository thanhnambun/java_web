package com.data.ss14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RequestMapping("B3")
@Controller
public class B3Controller {

    @Autowired
    private MessageSource messageSource;

    @GetMapping({"/home"})
    public String showHomePage(Model model, Locale locale, HttpServletRequest request) {
        model.addAttribute("title", messageSource.getMessage("title", null, locale));
        model.addAttribute("instruction", messageSource.getMessage("instruction", null, locale));
        model.addAttribute("greeting", messageSource.getMessage("greeting", null, locale));
        model.addAttribute("languageSelect", messageSource.getMessage("language.select", null, locale));
        model.addAttribute("languageEnglish", messageSource.getMessage("language.english", null, locale));
        model.addAttribute("languageVietnamese", messageSource.getMessage("language.vietnamese", null, locale));
        model.addAttribute("submit", messageSource.getMessage("submit", null, locale));

        String currentLanguage = "en";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("language".equals(cookie.getName())) {
                    currentLanguage = cookie.getValue();
                    break;
                }
            }
        }
        model.addAttribute("currentLanguage", currentLanguage);
        return "B3/home";
    }

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam(value = "lang", required = false, defaultValue = "en") String lang,
                                 HttpServletResponse response,
                                 HttpServletRequest request) {
        Cookie cookie = new Cookie("language", lang);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        LocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setLocale(request, response, new Locale(lang));

        return "redirect:/B3/home";
    }
}