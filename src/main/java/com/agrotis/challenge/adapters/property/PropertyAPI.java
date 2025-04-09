package com.agrotis.challenge.adapters.property;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.agrotis.base.path}/${spring.agrotis.base.version}/property")
public interface PropertyAPI {
}
