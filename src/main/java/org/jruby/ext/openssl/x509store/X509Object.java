/***** BEGIN LICENSE BLOCK *****
 * Version: EPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Eclipse Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/epl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2006 Ola Bini <ola@ologix.com>
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the EPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the EPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jruby.ext.openssl.x509store;

import java.util.Collection;
import java.util.List;

/**
 * c: X509_OBJECT
 *
 * @author <a href="mailto:ola.bini@ki.se">Ola Bini</a>
 */
public abstract class X509Object implements Comparable<X509Object> {

    /**
     * c: X509_OBJECT_idx_by_subject
     */
    public static int indexBySubject(final List<? extends X509Object> list, int type, Name name) {
        for ( int i = 0; i < list.size(); i++ ) {
            final X509Object obj = list.get(i);
            if ( type == obj.type() && obj.isName(name) ) return i;
        }
        return -1;
    }

    /**
     * c: X509_OBJECT_retrieve_by_subject
     */
    public static X509Object retrieveBySubject(final Collection<? extends X509Object> list, int type, Name name) {
        for ( X509Object obj : list ) {
            if ( type == obj.type() && obj.isName(name) ) return obj;
        }
        return null;
    }

    /**
     * c: X509_OBJECT_retrieve_match
     */
    public static X509Object retrieveMatch(final Collection<? extends X509Object> list, X509Object x) {
        for ( X509Object obj : list ) {
            if ( obj.matches(x) ) return obj;
        }
        return null;
    }

    public abstract boolean isName(Name nm) ;

    public abstract boolean matches(X509Object o) ;

    public abstract int type() ;

    public int compareTo(X509Object other) {
        return type() - other.type();
    }

}// X509_OBJECT
