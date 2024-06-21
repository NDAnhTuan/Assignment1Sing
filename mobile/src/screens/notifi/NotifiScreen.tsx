import {View, Image} from 'react-native';
import React from 'react';
import {appColors} from './../../constants/appColors';
import {appInfo} from '../../constants/appInfos';
import {fontFamilies} from '../../constants/fontFamilies';
import {ButtonComponent, SpaceComponent, TextComponent} from '../../components';

const NotifiScreen = () => {
  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: appColors.white,
      }}>
      <TextComponent
        text="Notifications"
        color={appColors.darkblue}
        size={22}
        font={fontFamilies.bold}
        styles={{textAlign: 'center'}}
      />
    </View>
  );
};

export default NotifiScreen;
