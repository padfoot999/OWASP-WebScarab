/***********************************************************************
 *
 * $CVSHeader$
 *
 * This file is part of WebScarab, an Open Web Application Security
 * Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2004 Rogan Dawes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Getting Source
 * ==============
 *
 * Source for this application is maintained at Sourceforge.net, a
 * repository for free software projects.
 * 
 * For details, please see http://www.sourceforge.net/projects/owasp
 *
 */

/*
 * ProxyConfig.java
 *
 * Created on August 28, 2003, 9:30 PM
 */

package org.owasp.webscarab.ui.swing;

import org.owasp.webscarab.plugin.Framework;
import org.owasp.webscarab.httpclient.HTTPClientFactory;

import org.owasp.webscarab.model.Preferences;
import org.owasp.webscarab.util.W32WinInet;

import javax.swing.JOptionPane;

/**
 *
 * @author  rdawes
 */
public class ProxyConfig extends javax.swing.JDialog {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1655655396211378140L;
	private HTTPClientFactory _factory = HTTPClientFactory.getInstance();
    private Framework _framework;
    
    /** Creates new form ProxyConfig */
    public ProxyConfig(java.awt.Frame parent, Framework framework) {
        super(parent, true);
        _framework = framework;
        initComponents();
        
        httpProxyServerTextField.setText(_factory.getHttpProxy());
        httpProxyPortTextField.setText(Integer.toString(_factory.getHttpProxyPort()));
        
        httpsProxyServerTextField.setText(_factory.getHttpsProxy());
        httpsProxyPortTextField.setText(Integer.toString(_factory.getHttpsProxyPort()));
        
        String[] noproxies = _factory.getNoProxy();
        if (noproxies.length>0) {
            StringBuffer buff = new StringBuffer();
            buff.append(noproxies[0]);
            for (int i=1; i<noproxies.length;i++) {
                buff.append(", ").append(noproxies[i]);
            }
            noProxyTextArea.setText(buff.toString());
        } else {
            noProxyTextArea.setText("");
        }
        if (!W32WinInet.isAvailable()) {
            w32Button.getParent().remove(w32Button);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel2 = new javax.swing.JLabel();
        httpProxyServerTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        httpProxyPortTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        httpsProxyServerTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        httpsProxyPortTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noProxyTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        w32Button = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Config proxies");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel2.setText("HTTP  Proxy : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel2, gridBagConstraints);

        httpProxyServerTextField.setMinimumSize(new java.awt.Dimension(250, 20));
        httpProxyServerTextField.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(httpProxyServerTextField, gridBagConstraints);

        jLabel5.setText("Port :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel5, gridBagConstraints);

        httpProxyPortTextField.setMinimumSize(new java.awt.Dimension(60, 20));
        httpProxyPortTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(httpProxyPortTextField, gridBagConstraints);

        jLabel4.setText("HTTPS Proxy : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel4, gridBagConstraints);

        httpsProxyServerTextField.setMinimumSize(new java.awt.Dimension(250, 20));
        httpsProxyServerTextField.setPreferredSize(new java.awt.Dimension(250, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(httpsProxyServerTextField, gridBagConstraints);

        jLabel3.setText("Port :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel3, gridBagConstraints);

        httpsProxyPortTextField.setMinimumSize(new java.awt.Dimension(60, 20));
        httpsProxyPortTextField.setPreferredSize(new java.awt.Dimension(60, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(httpsProxyPortTextField, gridBagConstraints);

        jLabel1.setText("No Proxy : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel1, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(250, 48));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(250, 51));
        noProxyTextArea.setLineWrap(true);
        noProxyTextArea.setToolTipText("Enter a comma separated list of hosts that do not need to go through the proxy");
        noProxyTextArea.setMinimumSize(new java.awt.Dimension(250, 40));
        noProxyTextArea.setPreferredSize(new java.awt.Dimension(250, 40));
        jScrollPane1.setViewportView(noProxyTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        w32Button.setText("Get IE Settings");
        w32Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                w32ButtonActionPerformed(evt);
            }
        });

        jPanel1.add(w32Button);

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        jPanel1.add(applyButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel1.add(cancelButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }//GEN-END:initComponents

    private void w32ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_w32ButtonActionPerformed
        if (W32WinInet.isAvailable()) {
            String server = W32WinInet.getHttpProxyServer();
            httpProxyServerTextField.setText(server == null ? "" : server);
            int port = W32WinInet.getHttpProxyPort();
            if (port < 1) port = 3128;
            httpProxyPortTextField.setText(Integer.toString(port));
            
            server = W32WinInet.getHttpsProxyServer();
            httpsProxyServerTextField.setText(server == null ? "" : server);
            port = W32WinInet.getHttpsProxyPort();
            if (port < 1) port = 3128;
            httpsProxyPortTextField.setText(Integer.toString(port));
            
            String bypass = W32WinInet.getNoProxy();
            if (bypass == null) bypass = "";
            noProxyTextArea.setText(bypass.replaceAll(";",","));
        }
    }//GEN-LAST:event_w32ButtonActionPerformed
    
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        String error = null;
        String httpserver = httpProxyServerTextField.getText().trim();
        int httpport = -1;
        try {
            String p = httpProxyPortTextField.getText().trim();
            if (httpserver.equals("") && p.equals("")) p = "3128";
            httpport = Integer.parseInt(p);
            if (httpport<1 || httpport>65535) error = "HTTP Proxy port must be between 0 and 65536";
        } catch (NumberFormatException nfe) {
            error = "Error parsing the HTTP Proxy port number";
        }
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String httpsserver = httpsProxyServerTextField.getText().trim();
        int httpsport = -1;
        try {
            String p = httpsProxyPortTextField.getText().trim();
            if (httpsserver.equals("") && p.equals("")) p = "3128";
            httpsport = Integer.parseInt(p);
            if (httpsport<1 || httpsport>65535) error = "HTTPS Proxy port must be between 0 and 65536";
        } catch (NumberFormatException nfe) {
            error = "Error parsing the HTTPS Proxy port number";
        }
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String[] noproxies = noProxyTextArea.getText().trim().split(" *, *");
        
        boolean running = _framework.isRunning();
        if (running) {
            if (_framework.isBusy()) {
                String[] status = _framework.getStatus();
                JOptionPane.showMessageDialog(this, status, "Error - plugins are busy", JOptionPane.ERROR_MESSAGE);
                return;
            }
            _framework.stopPlugins();
        }
        _factory.setHttpProxy(httpserver, httpport);
        _factory.setHttpsProxy(httpsserver, httpsport);
        _factory.setNoProxy(noproxies);
        if (running) {
            _framework.startPlugins();
        }
        
        Preferences.setPreference("WebScarab.httpProxy", httpserver+":"+httpport);
        Preferences.setPreference("WebScarab.httpsProxy", httpsserver+":"+httpsport);
        Preferences.setPreference("WebScarab.noProxy", noProxyTextArea.getText());
        
        setVisible(false);
        dispose();
    }//GEN-LAST:event_applyButtonActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField httpProxyPortTextField;
    private javax.swing.JTextField httpProxyServerTextField;
    private javax.swing.JTextField httpsProxyPortTextField;
    private javax.swing.JTextField httpsProxyServerTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea noProxyTextArea;
    private javax.swing.JButton w32Button;
    // End of variables declaration//GEN-END:variables
    
}
