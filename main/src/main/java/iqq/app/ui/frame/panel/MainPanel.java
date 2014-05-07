package iqq.app.ui.frame.panel;

import com.alee.extended.image.WebDecoratedImage;
import com.alee.extended.image.WebImage;
import com.alee.extended.panel.CenterPanel;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.VerticalPanel;
import com.alee.extended.panel.WrapPanel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import iqq.api.bean.IMStatus;
import iqq.app.core.service.SkinService;
import iqq.app.ui.IMContentPanel;
import iqq.app.ui.IMPanel;
import iqq.app.ui.component.StatusButton;
import iqq.app.ui.component.TitleComponent;
import iqq.app.ui.frame.MainFrame;
import iqq.app.ui.frame.SkinFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Project  : iqq-projects
 * Author   : 承∮诺 < 6208317@qq.com >
 * Created  : 14-5-5
 * License  : Apache License 2.0
 */
public class MainPanel extends IMContentPanel {

    private MainFrame frame;
    private IMPanel headerPanel = new IMPanel();
    private IMPanel middlePanel = new IMPanel();
    private IMPanel footerPanel = new IMPanel();

    public MainPanel(MainFrame mainFrame) {
        frame = mainFrame;

        this.add(createHeader(), BorderLayout.NORTH);
        this.add(createFooter(), BorderLayout.SOUTH);
        this.add(createMiddle(), BorderLayout.CENTER);
    }

    private IMPanel createHeader() {
        // 标题控件
        TitleComponent titleComponent = new TitleComponent(frame);
        titleComponent.setShowSettingButton(false);
        titleComponent.setShowMaximizeButton(false);
        titleComponent.getSkinButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkinFrame skinFrame = new SkinFrame(frame.getContext());
                skinFrame.setVisible(true);
            }
        });
        headerPanel.add(titleComponent, BorderLayout.NORTH);

        IMPanel headerInfo = new IMPanel();
        // 头像
        WebDecoratedImage avatar = new WebDecoratedImage(
                frame.getResourceService().getIcon("icons/login/face.jpg", 50, 50) );
        avatar.setRound(5);
        avatar.setShadeWidth(1);
        avatar.setDrawBorder(false);
        GroupPanel avatarWrap = new GroupPanel(10, avatar);
        avatarWrap.setMargin(10);
        headerInfo.add(avatarWrap, BorderLayout.WEST);
        headerPanel.add(headerInfo, BorderLayout.CENTER);

        // 信息
        WebLabel nickLbl = new WebLabel("承∮诺");
        WebLabel sigLbl = new WebLabel("IQQ v3 版本，正在开发中...");
        nickLbl.setForeground(Color.decode("#000000"));
        nickLbl.setDrawShade(true);
        nickLbl.setShadeColor(Color.lightGray);
        nickLbl.setFontSize(14);
        sigLbl.setForeground(Color.decode("#151515"));
        sigLbl.setFontSize(12);
        GroupPanel nickAndStatus = new GroupPanel(3,
                new CenterPanel(new StatusButton(IMStatus.ONLINE, 13), false ,true), nickLbl);
        GroupPanel userInfo = new GroupPanel(5, false, nickAndStatus, sigLbl);
        userInfo.setMargin(0, 0, 5, 5);
        headerInfo.add(new CenterPanel(userInfo, false, true), BorderLayout.CENTER);

        // 搜索
        WebTextField searchFld = new WebTextField();
        WebImage searcheImg = new WebImage(frame.getSkinService().getIconByKey("main/searchIcon", 20, 20));
        searcheImg.setMargin(0, 3, 0, 3);
        searchFld.setPreferredSize(new Dimension(frame.getWidth(), 25));
        searchFld.setLeadingComponent(searcheImg);
        searchFld.setOpaque(false);
        searchFld.setInputPrompt(frame.getI18nService().getMessage("findContact"));
        searchFld.setInputPromptForeground(Color.decode("#D8D8D8"));
        searchFld.setPainter(frame.getSkinService().getPainterByKey("main/searchBg"));
        headerInfo.add(searchFld, BorderLayout.SOUTH);
        return headerPanel;
    }

    private IMPanel createMiddle() {

        return middlePanel;
    }

    private IMPanel createFooter() {
        return footerPanel;
    }

    @Override
    public void installSkin(SkinService skinService) {
        // 背景
        this.setPainter(skinService.getPainterByKey("skin/background"));
    }
}
