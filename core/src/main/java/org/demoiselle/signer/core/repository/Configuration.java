/*
 * Demoiselle Framework
 * Copyright (C) 2016 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package org.demoiselle.signer.core.repository;

import java.io.File;


public class Configuration {

    /**
     * System key to set online or offline mode
     */
    public static final String MODE_ONLINE = "signer.repository.online";

    /**
     * System key to set storage location of index file of revoked certificate lists.
     */
    public static final String CRL_INDEX = "signer.repository.crl.index";

    /**
     * System key to set storage location of path file of revoked certificate lists.
     */
    public static final String CRL_PATH = "signer.repository.crl.path";
    
    
    /**
     * System key to set storage location of path file of LPA 
     */
    public static final String LPA_PATH = "signer.repository.lpa.path";
    
    
    public static Configuration instance = new Configuration();

    
    /**
     * to static single instance
     *
     * @return instance of Configuration
     */
    public static Configuration getInstance() {
        return instance;
    }

    private String crlIndex;
    private String crlPath;
    private String lpaPath;    
    private boolean isOnline;

    /**
     * Check for system variables. If there is, assign in class variables otherwise use default values.
     */
    private Configuration() {
        String mode_online = (String) System.getProperties().get(MODE_ONLINE);
        if (mode_online == null || mode_online.isEmpty()) {
            setOnline(true);
        } else {
            setOnline(Boolean.valueOf(mode_online));
        }
        crlIndex = (String) System.getProperties().get(CRL_INDEX);
        if (crlIndex == null || crlIndex.isEmpty()) {
            setCrlIndex(".crl_index");
        }

        crlPath = (String) System.getProperties().get(CRL_PATH);
        if (crlPath == null || crlPath.equals("")) {
        	setCrlPath(System.getProperty("java.io.tmpdir") + File.separatorChar + "crls");
        }
        
        lpaPath = (String) System.getProperties().get(LPA_PATH);
        if (lpaPath == null || lpaPath.equals("")) {
            setLpaPath(System.getProperty("java.io.tmpdir") + File.separatorChar + "lpas");
        }
    }

    /**
     * Gets the location where the revoked certificate lists index file is stored
     *
     * @return location of CRL index file
     */
    public String getCrlIndex() {
        return crlIndex;
    }

    public void setCrlIndex(String crlIndex) {
        this.crlIndex = crlIndex;
    }

    /**
     * Returns whether the repository is in online (TRUE) or offline (FALSE) mode 
     *
     * @return true (online) or false (offline)
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * Determines whether the repository query should be done online or offline.
     *
     * @param isOnline True for online, False for offline.
     */
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    /**
     * Retrieves the location where the CRL(certificate revoked lists) repository is stored
     *
     * @return location of CRL repository 
     */
    public String getCrlPath() {
        return crlPath;
    }

    /**
     * Configures the location where the CRL (certificate revoked lists) repository will be stored
     *
     * @param crlPath path for CRL repository
     */
    public void setCrlPath(String crlPath) {
        this.crlPath = crlPath;
    }

    /** Retrieves the location where the LPA local repository is stored
     * 
     * @return location of local LPA repository
     */
	public String getLpaPath() {
		return lpaPath;
	}
	
	/**
	 * 
	 *  Configures the location where the LPA local repository will be stored
	 *  
	 * @param lpaPath path for LPA local repository
	 */

	public void setLpaPath(String lpaPath) {
		this.lpaPath = lpaPath;
	}

}
