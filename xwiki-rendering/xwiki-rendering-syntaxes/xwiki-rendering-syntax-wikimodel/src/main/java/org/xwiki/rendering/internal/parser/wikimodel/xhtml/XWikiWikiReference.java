/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.internal.parser.wikimodel.xhtml;

import org.wikimodel.wem.WikiParameter;
import org.wikimodel.wem.WikiParameters;
import org.wikimodel.wem.WikiReference;
import org.xwiki.rendering.listener.reference.ResourceReference;
import org.xwiki.rendering.listener.reference.ResourceType;

/**
 * WikiModel extension in order to add additional XWiki Link information so that the XWiki Generator Listener for the
 * XHTML syntax doesn't have to do any link parsing.
 *
 * @version $Id$
 * @since 2.5RC1
 */
public class XWikiWikiReference extends WikiReference
{
    private boolean isTyped;

    private ResourceType type;

    private boolean isFreeStanding;

    WikiParameters referenceParameters = WikiParameters.EMPTY;

    public XWikiWikiReference(boolean isTyped, ResourceType type, String reference, String label,
        WikiParameters referenceParameters, WikiParameters linkParameters, boolean isFreeStanding)
    {
        super(reference, label, linkParameters);
        this.isTyped = isTyped;
        this.type = type;
        this.isFreeStanding = isFreeStanding;
        this.referenceParameters = referenceParameters;
    }

    public boolean isTyped()
    {
        return this.isTyped;
    }
    
    public boolean isFreeStanding()
    {
        return this.isFreeStanding;
    }

    public ResourceType getType()
    {
        return this.type;
    }

    public WikiParameters getReferenceParameters()
    {
        return this.referenceParameters;
    }

    public ResourceReference getReference()
    {
        ResourceReference resourceReference = new ResourceReference(getLink(), getType());
        resourceReference.setTyped(isTyped());
        for (WikiParameter param : getReferenceParameters()) {
            resourceReference.setParameter(param.getKey(), param.getValue());
        }
        return resourceReference;
    }

    // TODO: implement hashcode, equals, totring
}
