/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.applicationmvc.controller;

import com.mycompany.applicationmvc.Bean.DanhMucBean;
import com.mycompany.applicationmvc.model.MainModel;
import com.mycompany.applicationmvc.view.DonBaoDuongPanel;
import com.mycompany.applicationmvc.view.HomePanel;
import com.mycompany.applicationmvc.view.KhachHangPanel;
import com.mycompany.applicationmvc.view.LinhKienPanel;
import com.mycompany.applicationmvc.view.XePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MinhTo
 */
public class SwitchController {
    private JFrame jFrameMain;
    private JPanel jPaneRoot;
    private String kindSelected;
    
    List<DanhMucBean> listDanhMucBeans;
    
    MainModel M_Model;
    
    public SwitchController(JFrame jFrameMain,JPanel jPaneRoot, List<DanhMucBean> listDanhMucBeans) {
        M_Model = new MainModel();
        this.jFrameMain=jFrameMain;
        this.jPaneRoot = jPaneRoot;
        this.listDanhMucBeans = listDanhMucBeans;
    }
    
    public JPanel getjPaneRoot() {
        return jPaneRoot;
    }

    public void setjPaneRoot(JPanel jPaneRoot) {
        this.jPaneRoot = jPaneRoot;
    }

    public String getKindSelected() {
        return kindSelected;
    }

    public void setKindSelected(String kindSelected) {
        this.kindSelected = kindSelected;
    }

    public JFrame getjFrameMain() {
        return jFrameMain;
    }

    public void setjFrameMain(JFrame jFrameMain) {
        this.jFrameMain = jFrameMain;
    }

  
 
    public void setDashBoard(JPanel jpn,JLabel jlb)
    {
        kindSelected="trangChinh";
        
    }
    
    public void setEvent()
    {
        for (DanhMucBean danhMucBean : listDanhMucBeans){
            danhMucBean.getJlb().addMouseListener(new LabelEvent(danhMucBean.getKind(),danhMucBean.getJpn(),null,danhMucBean.getJlb()));
        }
    }
    
  class LabelEvent extends MouseAdapter {
        String kind;  // thể loại Panel cần hiện thị
        JPanel jpn;// chua label
        JPanel node; //panel cần hiển thị
        JLabel label;

        public LabelEvent(String kind, JPanel jpn, JPanel node, JLabel label) {
            this.kind = kind;
            this.jpn = jpn;
            this.node = node;
            this.label = label;
        }
       
        @Override
        public void mouseClicked(MouseEvent e) {
            setKindSelected(kind);
            
            if("trangChinh".equals(kind))
            {
                node=new HomePanel();
            }
            else if(kind.equals("khachHangPage"))
            {
                node=new KhachHangPanel(jFrameMain);
            }
            else if(kind.equals("xePage"))
            {
                node=new XePanel();
            }
            else if(kind.equals("linhKienPage"))
            {
                node=new LinhKienPanel();
            }
            else if(kind.equals("donBaoDuongPage")){
                node = new DonBaoDuongPanel();
                new DonBaoDuongController((DonBaoDuongPanel)node, M_Model.DBDModel);
                
            }
           getjPaneRoot().removeAll();
           getjPaneRoot().setLayout(new BorderLayout());
           getjPaneRoot().add(node);
           getjPaneRoot().validate(); // nếu một compontent có đầy đủ kích thước khi đó nó duocjd gọi là valid
           getjPaneRoot().repaint();
           setChangeBackground(kind); // tô màu Danh mục panel,lable 
        }
        
//        @Override
//        public void mouseExited(MouseEvent e) {
//            super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
//            if(!kindSelected.equals(kind))
//                {
//                    label.setBackground(new Color(76, 175, 80)); // khi chọn loại Panel khấc
//                }
//        }
    }
   public void setChangeBackground(String kind)
            {
                for (DanhMucBean ds :listDanhMucBeans ) {
                    if(ds.getKind().equals(kind))
                    ds.getJlb().setBackground(new Color(96, 100, 191));  // khi dduojc nhán                  
                }
            }
       
            
}
