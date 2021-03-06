/*******************************************************************************
 * This file is part of the EEG-database project
 *
 * ==========================================
 *
 * Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 * ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * ***********************************************************************************************************************
 *
 * AbstractUITest.java, 2014/09/10 00:01 Jan Stebetak
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.ui;

import net.sourceforge.jwebunit.junit.WebTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.*;

/**
 * Created by stebjan on 10.9.14.
 */

@ContextConfiguration(locations = {"classpath:/web-test-context.xml"})
public abstract class AbstractUITest extends AbstractTestNGSpringContextTests {
    protected WebTester tester;

    protected int waitForAjax = 10000;

    protected String url = "http://localhost:8082";


    protected void createGroupIfNotExists() throws InterruptedException, IOException {
        tester.clickLinkWithText(getProperty("menuItem.groups"));
        try {
            tester.assertTextPresent("new group");
        } catch (AssertionError ex) {
            tester.clickLinkWithText(getProperty("menuItem.createGroup"));
            tester.setTextField("title", "new group");
            tester.setTextField("description", "description");
            tester.clickButtonWithText(getProperty("button.save"));
            Thread.sleep(waitForAjax);
        }
    }

    protected String getProperty(String key) throws IOException {
        String propFile = "/EEGDataBaseApplication.properties";
        Properties prop = new Properties();
        assertNotNull(getClass().getResource(propFile));
        prop.load(getClass().getResourceAsStream(propFile));
        return prop.getProperty(key);
    }
}

