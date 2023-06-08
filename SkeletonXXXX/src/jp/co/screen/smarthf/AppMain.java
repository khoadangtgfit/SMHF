/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * 本プログラムの著作権は大日本スクリーン製造株式会社に帰属するものであり、
 * 同社はこれを営業秘密として管理するものです。従い、本プログラムの全て、
 * 一部にかかわらず、その複製、頒布を行うことは、同社の事前の書面による
 * 承諾がない限り固く禁じられるものです。
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import jp.co.screen.egleg.util.fstr.FolderStructure;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.ConfigManager;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.log.AppLogger;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.view.main.SmartHFManagerFrm;
import jp.co.screen.tool.commonutil.EquiosChecker;

import org.apache.log4j.Logger;

/**
 * Load Balance Recorder setting tool main class
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class AppMain {
	
	
	private static Logger LOGGER=Logger.getLogger(SmartHFManagerFrm.class);
	
	public static void main(String[] args){
		 // init log
	    AppLogger.init();
	    LOGGER.info("Start application.");
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        LOGGER.info(e.toString());
	        e.printStackTrace();
	    }
	    
	    if( ! ConfigManager.loadXXXXConfig()){
            return;
        }
	    
	    if(!ConfigManager.checkSingleInstance()){
            return;
        }
	    
	    boolean isEquiosRunning= false;
	    
	    try{
	        String str = "D:\\EquiosCenter\\bin\\ServiceCtrl";
//            isEquiosRunning= EquiosChecker.isEquiosRunning(FolderStructure.getPath(Constants.KEY_SYS_TOOL_SERVICECTRL));
            isEquiosRunning= EquiosChecker.isEquiosRunning(str);
        } catch (Exception e){
            LOGGER.error("Fail to check running Equios, open in default mode");
            LOGGER.error(e.toString());
        }
        
        if( isEquiosRunning){
            if( ! confirmReadOnlyMode()){
                LOGGER.info("Exit program");
                return;
            }else{
                LOGGER.info("Start in Readonly mode");
            }
        }
        
        
        SmartHFManagerFrm dlg= new SmartHFManagerFrm(isEquiosRunning);
        dlg.setVisible(true); 
        
	}
	
	/**
	 * confirm read only mode
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private static boolean confirmReadOnlyMode() {
        int confirmed = JOptionPane.showConfirmDialog(null,
                SmartHFResource.getResource(LangKey.MSG_INF_READONLYMODECONFIRM), 
                SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION),
                JOptionPane.YES_NO_OPTION);
        return (confirmed == JOptionPane.YES_OPTION);
    }
}
