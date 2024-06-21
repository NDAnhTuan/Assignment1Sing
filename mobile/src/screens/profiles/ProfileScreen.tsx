import {View, Image} from 'react-native';
import React from 'react';
import {appColors} from './../../constants/appColors';
import {appInfo} from '../../constants/appInfos';
import {fontFamilies} from '../../constants/fontFamilies';
import {ButtonComponent, SpaceComponent, TextComponent} from '../../components';

const ProfileScreen = () => {
  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: appColors.primary,
      }}>
      <Image
        source={require('../../assets/images/avatar.png')}
        style={{
          width: appInfo.sizes.WIDTH * 0.7,
          resizeMode: 'contain',
        }}
      />
      <SpaceComponent height={25} />
      <TextComponent
        text="Tung Nguyen"
        color={appColors.white}
        size={22}
        font={fontFamilies.bold}
        styles={{textAlign: 'center'}}
      />
    </View>
  );
};

export default ProfileScreen;
