package com.marvinsworld.dconfig.spring.annotation;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * Description:解析&lt;dconfig:config&gt;和&lt;dconfig:annotation-driven&gt;
 *
 * @author Marvinsworld
 * @since 2015/12/6 9:54
 */
class DConfigNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        //匹配两种dconfig:config和dconfig:annotation-driven
        registerBeanDefinitionParser("config", new DconfigParser());
    }

    static class DconfigParser extends AbstractSingleBeanDefinitionParser {
        protected Class<?> getBeanClass(Element element) {
            return DConfigAnnotationProcessor.class;
        }

        protected boolean shouldGenerateId() {
            return true;
        }

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            super.doParse(element, parserContext, builder);

            String locations = DomUtils.getChildElementByTagName(element, "locations").getAttribute("value");
            if (StringUtils.hasLength(locations)) {
                String[] ids = StringUtils.commaDelimitedListToStringArray(locations);
                builder.addConstructorArgValue(ids);
            }

            String namespace = DomUtils.getChildElementByTagName(element, "namespace").getAttribute("value");
            if (StringUtils.hasLength(namespace)) {
                builder.addConstructorArgValue(namespace);
            }

            String registry = DomUtils.getChildElementByTagName(element, "registry").getAttribute("value");
            if (StringUtils.hasLength(registry)) {
                builder.addConstructorArgValue(registry);
            }

//            builder.addPropertyValue("locations",
//                    parserContext.getDelegate().parseCustomElement(DomUtils.getChildElementByTagName(element, "locations"),builder.getRawBeanDefinition()));

        }

//        protected void doParse(Element element, BeanDefinitionBuilder builder) {
//            if (element.getLocalName().equals("annotation-driven")) {
//                return;
//            }
//            String location = element.getAttribute("locations");
//            if (StringUtils.hasLength(location)) {
//                String[] ids = StringUtils.commaDelimitedListToStringArray(location);
//                builder.addConstructorArgValue(ids);
//            }
//
//            String zkAddress = element.getAttribute("zk-address");
//            if (StringUtils.hasLength(zkAddress)) {
//                builder.addConstructorArgValue(zkAddress);
//            }
//
//            String namespace = element.getAttribute("namespace");
//            if (StringUtils.hasLength(namespace)) {
//                builder.addConstructorArgValue(namespace);
//            }
//            String order = element.getAttribute("order");
//            if (StringUtils.hasLength(order)) {
//                builder.addPropertyValue("order", Integer.valueOf(order));
//            }
//            builder.addPropertyValue("timeout", Integer.valueOf(element.getAttribute("timeout")));
//            builder.addPropertyValue("ignoreResourceNotFound", Boolean.valueOf(element.getAttribute("ignore-resource-not-found")));
//
//            builder.addPropertyValue("ignoreUnresolvablePlaceholders", Boolean.valueOf(element.getAttribute("ignore-unresolvable")));
//        }
    }
}
