package com.marvinsworld.dconfig.spring.annotation;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
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
        registerBeanDefinitionParser("locations", new LocationsParser());
        registerBeanDefinitionParser("zk-address", new ZkAddressParser());
        registerBeanDefinitionParser("namespace", new NamespaceParser());
        //registerBeanDefinitionParser("annotation-driven", new Parser());
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

//            builder.addPropertyValue("locations", parserContext.getDelegate()
//                    .parseCustomElement(
//                            DomUtils.getChildElementByTagName(element, "locations"),
//                            builder.getRawBeanDefinition()));
//
//            builder.addPropertyValue("zk-address", parserContext.getDelegate()
//                    .parseCustomElement(
//                            DomUtils.getChildElementByTagName(element, "zk-address"),
//                            builder.getRawBeanDefinition()));
//
//            builder.addPropertyValue("namespace", parserContext.getDelegate()
//                    .parseCustomElement(
//                            DomUtils.getChildElementByTagName(element, "namespace"),
//                            builder.getRawBeanDefinition()));
        }

        protected void doParse(Element element, BeanDefinitionBuilder builder) {

//            if (element.getLocalName().equals("annotation-driven")) {
//                return;
//            }
            String location = element.getAttribute("locations");
            if (StringUtils.hasLength(location)) {
                String[] ids = StringUtils.commaDelimitedListToStringArray(location);
                builder.addConstructorArgValue(ids);
            }

            String zkAddress = element.getAttribute("zk-address");
            if (StringUtils.hasLength(zkAddress)) {
                builder.addConstructorArgValue(zkAddress);
            }

            String namespace = element.getAttribute("namespace");
            if (StringUtils.hasLength(namespace)) {
                builder.addConstructorArgValue(namespace);
            }

//            String order = element.getAttribute("order");
//            if (StringUtils.hasLength(order)) {
//                builder.addPropertyValue("order", Integer.valueOf(order));
//            }
//            builder.addPropertyValue("timeout", Integer.valueOf(element.getAttribute("timeout")));
//            builder.addPropertyValue("ignoreResourceNotFound", Boolean.valueOf(element.getAttribute("ignore-resource-not-found")));
//
//            builder.addPropertyValue("ignoreUnresolvablePlaceholders", Boolean.valueOf(element.getAttribute("ignore-unresolvable")));
        }
    }

    static class LocationsParser extends AbstractSingleBeanDefinitionParser {
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            builder.addConstructorArgValue(element.getAttribute("value"));

            //builder.addPropertyValue("locations", element.getAttribute("value"));
        }
    }

    static class ZkAddressParser extends AbstractSingleBeanDefinitionParser {
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            builder.addConstructorArgValue(element.getAttribute("value"));

            //builder.addPropertyValue("zk-address", element.getAttribute("value"));
        }
    }

    static class NamespaceParser extends AbstractSingleBeanDefinitionParser {
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            builder.addConstructorArgValue(element.getAttribute("value"));

            //builder.addPropertyValue("namespace", element.getAttribute("value"));
        }
    }


}
