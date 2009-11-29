/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.map.DataMap;
import org.apache.cayenne.map.MapLoader;
import org.apache.cayenne.resource.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

/**
 * @since 3.1
 */
public class XMLDataMapLoader implements DataMapLoader {

    private static Log logger = LogFactory.getLog(XMLDataMapLoader.class);

    public DataMap load(
            DataChannelDescriptor parentDataChannelDescriptor,
            String location,
            String name) throws CayenneRuntimeException {

        Resource baseResource = parentDataChannelDescriptor.getConfigurationResource();
        if (baseResource == null) {
            throw new NullPointerException("Null parentDataChannelDescriptor's resource");
        }

        Resource dataMapResource = baseResource.getRelativeResource(location);

        // TODO: andrus 11.27.2009 - deprecate MapLoader and implement a loader
        // here. MapLoader is in the wrong place, exposes ContentHandler methods and
        // implements if/else contextless matching of tags... should use something like
        // SAXNestedTagHandler instead.
        MapLoader mapLoader = new MapLoader();
        URL url = dataMapResource.getURL();

        InputStream in = null;
        
        DataMap map;

        try {
            in = url.openStream();

            map = mapLoader.loadDataMap(new InputSource(in));
        }
        catch (Exception e) {
            throw new CayenneRuntimeException("Error loading configuration from %s", e, url);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ioex) {
                logger.info(
                        "failure closing input stream for " + url + ", ignoring",
                        ioex);
            }
        }
        
        map.setName(name);
        map.setLocation(location);
        return map;
    }
}
