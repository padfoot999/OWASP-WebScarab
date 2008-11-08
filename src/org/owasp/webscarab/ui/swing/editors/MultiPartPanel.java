/*
 * MultiPartEditor.java
 *
 * Created on 16 December 2004, 03:39
 */

package org.owasp.webscarab.ui.swing.editors;

import javax.swing.JSplitPane;

import org.owasp.webscarab.model.MultiPartContent;
import org.owasp.webscarab.model.Message;

import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.owasp.webscarab.ui.swing.MessagePanel;

/**
 *
 * @author  rogan
 */
public class MultiPartPanel extends javax.swing.JPanel implements ByteArrayEditor {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7790954945202743857L;
	private byte[] _data = null;
    private boolean _modified = false;
    private boolean _editable = false;
    
    private MultiPartContent _content = null;
    private PartsListModel _partsList = new PartsListModel();
    private int _selected = -1;
    
    private MessagePanel _mp = null;
    
    /** Creates new form MultiPartEditor */
    public MultiPartPanel() {
        initComponents();
        setName("MultiPart");
        
        _mp = new MessagePanel(JSplitPane.HORIZONTAL_SPLIT);
        contentPanel.add(_mp);
        
        partList.setModel(_partsList);
        partList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        partList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) return;
                if (_editable && _mp.isModified() && _selected > -1) {
                    _modified = true;
                    Message message = _mp.getMessage();
                    _content.set(_selected, message);
                    _partsList.fireContentsChanged(_selected, _selected);
                }
                _selected = partList.getSelectedIndex();
                if (_selected < 0)
                    return;
                Message message = _content.get(_selected);
                _mp.setMessage(message);
                invalidate();
                revalidate();
            }
        });
    }
    
    public void setEditable(boolean editable) {
        _editable = editable;
        _mp.setEditable(editable);
    }
    
    public void setBytes(String contentType, byte[] bytes) {
        int size = 0;
        if (_content != null) {
            size = _content.size();
        }
        _modified = false;
        _data = bytes;
        _content = new MultiPartContent(contentType, bytes);
        if (size>0) {
            _partsList.fireIntervalRemoved(0, size-1);
        }
        if (_content.size()>0) {
            _partsList.fireIntervalAdded(0, _content.size()-1);
        }
    }
    
    public boolean isModified() {
        return (_editable && (_modified || _mp.isModified()));
    }
    
    public byte[] getBytes() {
        if (_editable && isModified()) {
            if (_mp.isModified()) {
                _content.set(_selected, _mp.getMessage());
            }
            _data = _content.getBytes();
            _modified = false;
        }
        return _data;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        partList = new javax.swing.JList();
        contentPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 131));
        jScrollPane1.setViewportView(partList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        contentPanel.setLayout(new java.awt.BorderLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(contentPanel, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList partList;
    // End of variables declaration//GEN-END:variables
    
    private class PartsListModel extends AbstractListModel {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 690901016063929348L;

		public Object getElementAt(int index) {
            return _content.getPartName(index);
        }
        
        public int getSize() {
            if (_content == null) return 0;
            return _content.size();
        }
        
        public void fireIntervalAdded(int index0, int index1) {
            super.fireIntervalAdded(PartsListModel.this, index0, index1);
        }
        
        public void fireIntervalRemoved(int index0, int index1) {
            super.fireIntervalRemoved(PartsListModel.this, index0, index1);
        }
        
        public void fireContentsChanged(int index0, int index1) {
            super.fireContentsChanged(PartsListModel.this, index0, index1);
        }
        
    }
    
}
