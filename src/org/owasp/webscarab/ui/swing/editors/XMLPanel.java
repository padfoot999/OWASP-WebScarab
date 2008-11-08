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
 * XMLPanel.java
 *
 * Created on November 4, 2003, 8:23 AM
 */

package org.owasp.webscarab.ui.swing.editors;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import org.owasp.webscarab.util.swing.TreeUtil;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.awt.Component;
import javax.swing.JTree;
import org.owasp.webscarab.util.swing.MultiLineTreeCellRenderer;
import org.owasp.webscarab.util.swing.DOMTreeModel;

import org.ccil.cowan.tagsoup.Parser;
import org.owasp.webscarab.util.DOMHandler;
import org.xml.sax.InputSource;

/**
 *
 * @author  rdawes
 */
public class XMLPanel extends javax.swing.JPanel implements ByteArrayEditor {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -244729718376716387L;
	private boolean _editable = false;
    private boolean _modified = false;
    
    private byte[] _data = new byte[0];
    
    /** Creates new form XMLPanel */
    public XMLPanel() {
        initComponents();
        setName("XML");
        xmlTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("No content")));
        xmlTree.setCellRenderer(new XMLTreeNodeRenderer());
    }
    
    public void setEditable(boolean editable) {
    }
    
    public void setBytes(String contentType, byte[] bytes) {
        _data = bytes;
        if (bytes != null) {
            InputStream is = new ByteArrayInputStream(bytes);
            Element rootElement = null;
            try {
                Document document = null;
                if (contentType.matches("text/xml.*")) {
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    builderFactory.setIgnoringElementContentWhitespace(true);
                    DocumentBuilder builder = builderFactory.newDocumentBuilder();
                    document = builder.parse(is);
                } else if (contentType.matches("text/html.*")) {
                    DOMHandler domHandler = new DOMHandler();
                    Parser parser = new Parser();
                    parser.setContentHandler(domHandler);
                    parser.parse(new InputSource(is));
                    document = domHandler.getDocument();
                }
                if (document != null) {
                    document.getDocumentElement().normalize();
                    rootElement = document.getDocumentElement();
                    TreeModel tm = new DOMTreeModel(rootElement);
                    xmlTree.setModel(tm);
                } else {
                    xmlTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Invalid content")));
                }
            } catch (Exception e) {
                xmlTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Error: " + e.getMessage())));
                e.printStackTrace();
            }
        } else {
            xmlTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("No elements")));
        }
        TreeUtil.expandAll(xmlTree, true);
    }
    
    public boolean isModified() {
        return _editable && _modified;
    }
    
    public byte[] getBytes() {
        return _data;
    }
    
    private TreeModel getTreeModel(byte[] bytes) {
        return null;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        treeScrollPane = new javax.swing.JScrollPane();
        xmlTree = new javax.swing.JTree();

        setLayout(new java.awt.BorderLayout());

        treeScrollPane.setViewportView(xmlTree);

        add(treeScrollPane, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane treeScrollPane;
    private javax.swing.JTree xmlTree;
    // End of variables declaration//GEN-END:variables
    
    private class XMLTreeNodeRenderer extends MultiLineTreeCellRenderer {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 4591128488009949423L;

		/** Creates a new instance of UrlTreeRenderer */
        public XMLTreeNodeRenderer() {
        }
        
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            if (value instanceof Node) {
                Node node = (Node) value;
                String text = value.toString();
                int type = node.getNodeType();
//                System.out.println("Type: " + type + ": " + node.toString());
                switch (type) {
                    case Node.ATTRIBUTE_NODE: text = "ATTRIBUTE_NODE"; break;
                    case Node.CDATA_SECTION_NODE: text = "CDATA_SECTION_NODE"; break;
                    case Node.COMMENT_NODE: text = "COMMENT_NODE"; break;
                    case Node.ELEMENT_NODE:
                        text = "<" + node.getNodeName();
                        NamedNodeMap nnm = node.getAttributes();
                        if (nnm.getLength()>0) {
                            StringBuffer buff = new StringBuffer();
                            Node attr = nnm.item(0);
                            buff.append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
                            for (int i=1; i<nnm.getLength();i++) {
                                attr = nnm.item(i);
                                buff.append(" ").append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\"");
                            }
                            text = text + " " + buff.toString();
                        }
                        text = text + ">";
                        break;
                    case Node.TEXT_NODE: text = node.getNodeValue(); break;
                    default: value = "Type: " + node.getNodeType() + node.toString();
                }
                value = text.trim();
            }
            return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        javax.swing.JFrame top = new javax.swing.JFrame("XML Editor");
        top.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        
        byte[] bytes;
        if (args.length > 0) {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            InputStream is = new java.io.FileInputStream(args[0]);
            byte[] buff = new byte[1024];
            int got;
            while ((got = is.read(buff)) > 0) {
                baos.write(buff,0,got);
            }
            bytes = baos.toByteArray();
        } else {
            bytes = ("<b>NOTE: For security reasons, using the administration webapp\n" +
            "        is restricted to users with role \"admin\". The manager webapp\n" + 
            "is restricted to users with role \"manager\".</b>").getBytes();
        }
        XMLPanel xp = new XMLPanel();
        top.getContentPane().add(xp);
        top.setBounds(100,100,600,400);
        try {
            xp.setBytes("text/xml",bytes);
            xp.setEditable(true);
            top.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
