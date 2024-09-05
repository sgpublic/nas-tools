# -*- coding: utf-8 -*-
from datetime import datetime
import os
import re

from lxml import etree

import log
from app.utils import SystemUtils, ExceptionUtils
from config import RMT_SUBEXT, Config


class SiteHelper:
    _LOGOUT_USER_PANEL = [
        '//a[contains(@href, "logout")]'
        '//a[contains(@data-url, "logout")]'
        '//a[contains(@href, "mybonus")]'
        '//a[contains(@onclick, "logout")]'
        '//a[contains(@href, "usercp")]',
        '//form[contains(@action, "logout")]',
        '//div[@class="user-info-side"]',
        '//div[@id="main_succeed" and not(contains(@style, "none"))]',
        '//a[@id="myitem"]',
        '//a[@href="/users/profile"]',
    ]

    @classmethod
    def _logout_user_panel(cls):
        internal_conf = list(cls._LOGOUT_USER_PANEL)
        custom_conf = os.path.join(Config().get_config_path(), "siteconf/LOGOUT_USER_PANEL.txt")
        if os.path.exists(custom_conf):
            try:
                with open(custom_conf, 'r') as conf:
                    for line in conf:
                        line = line.strip()
                        if line != "":
                            internal_conf.append(line)
            except Exception as e:
                ExceptionUtils.exception_traceback(e)
                log.error(f"【User】读取 LOGOUT_USER_PANNEL 出错：{e}")
        return internal_conf

    @classmethod
    def is_logged_in(cls, html_text):
        """
        判断站点是否已经登陆
        :param html_text:
        :return:
        """
        html = etree.HTML(html_text)
        if not html:
            return False
        # 存在明显的密码输入框，说明未登录
        if html.xpath("//input[@type='password']"):
            return False
        # 是否存在登出和用户面板等链接
        xpaths = cls._logout_user_panel()
        for xpath in xpaths:
            if html.xpath(xpath):
                return True
        user_info_div = html.xpath('')
        if user_info_div:
            return True

        return False

    @staticmethod
    def get_url_subtitle_name(disposition, url):
        """
        从站点下载请求中获取字幕文件名
        """
        fname = re.findall(r"filename=\"?(.+)\"?", disposition or "")
        if fname:
            fname = str(fname[0].encode('ISO-8859-1').decode()).split(";")[0].strip()
            if fname.endswith('"'):
                fname = fname[:-1]
        elif url and os.path.splitext(url)[-1] in (RMT_SUBEXT + ['.zip']):
            fname = url.split("/")[-1]
        else:
            fname = str(datetime.now())
        return fname

    @staticmethod
    def transfer_subtitle(source_sub_file, media_file):
        """
        转移站点字幕
        """
        new_sub_file = "%s%s" % (os.path.splitext(media_file)[0], os.path.splitext(source_sub_file)[-1])
        if os.path.exists(new_sub_file):
            return 1
        else:
            return SystemUtils.copy(source_sub_file, new_sub_file)
