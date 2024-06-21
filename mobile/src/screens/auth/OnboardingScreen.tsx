import React from 'react';
import {Image, View} from 'react-native';
import {appColors} from '../../constants/appColors';
import {appInfo} from '../../constants/appInfos';
import {globalStyles} from '../../styles/globalStyles';
import {ButtonComponent, TextComponent} from '../../components';
import {fontFamilies} from '../../constants/fontFamilies';

const OnboardingScreen = ({navigation}: any) => {
  return (
    <View style={[globalStyles.container, {justifyContent: 'space-between'}]}>
      {/* Logo */}
      <View style={{alignItems: 'center', marginTop: 200}}>
        <Image
          source={require('../../assets/images/logo-singalarity-1.png')}
          style={{width: appInfo.sizes.WIDTH * 0.6, resizeMode: 'contain'}}
        />
        <TextComponent
          text="Let's get started!"
          color={appColors.darkblue}
          size={22}
          font={fontFamilies.bold}
          styles={{textAlign: 'center'}}
        />
        <TextComponent
          text="Login to enjoy the features we've"
          color={appColors.darkblue}
          size={16}
          font={fontFamilies.regular}
          styles={{textAlign: 'center', marginTop: 15}}
        />
        <TextComponent
          text="provided, and stay healthy!"
          color={appColors.darkblue}
          size={16}
          font={fontFamilies.regular}
          styles={{textAlign: 'center', marginTop: 3}}
        />
      </View>

      {/* Buttons */}
      <View style={[globalStyles.container, {justifyContent: 'space-between'}]}>
        <View style={{alignItems: 'center', marginTop: 150}}>
          <ButtonComponent
            color={appColors.darkblue}
            styles={{
              width: 263,
              height: 56,
              borderRadius: 32,
            }}
            onPress={() => navigation.navigate('LoginScreen')}
            text="Login"
            type="primary"
          />
          <ButtonComponent
            color={appColors.white}
            styles={{
              width: 263,
              height: 56,
              borderRadius: 32,
              borderColor: appColors.darkblue,
              borderWidth: 1,
            }}
            textColor={appColors.darkblue}
            onPress={() => navigation.navigate('SignUpScreen')}
            text="Sign Up"
            type="primary"
          />
        </View>
      </View>
    </View>
  );
};

export default OnboardingScreen;
