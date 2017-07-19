package com.dao;

import com.entity.Client;

public interface CilentDAO {
    // 返回0表示用户已经存在，无需登记，返回表示新用户登记成功
    int New_Cilent(String ch_name, String en_name, String sex, String idcard_type, String idcard_num,
                   String nickname, String type, String address, String nat, String address_num, String city, String city_num,
                   String phone, String email);

    // 开户方法，返回0表示还未新用户登记信息，返回1表示成功，返回2表示开户失败：同一个人在同一家银行(以法人为单位)只能开立一个Ⅰ类账户,请办理Ⅱ类或Ⅲ类
    int New_Card(String ch_name, String id_card_num, String card_type, String net_id, String phone,
                 String password);

    // 销户方法，返回0表示银行卡号错误或者用户信息错误，返回1表示销户成功，返回2表示卡内还有余额或欠费请取款后销户
    int Closing_a_card(String ch_name, String idcard_num, int card_id, String password);

    //返回银行卡信息
    Client findCardInfoByClient_idAndClient_card_type(int client_id, String client_card_type);
}
