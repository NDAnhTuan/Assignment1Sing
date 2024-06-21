import React, {useEffect, useState} from 'react';
import {SplashScreen} from '../screens';
import MainNavigator from './MainNavigator';

const AppRouters = () => {
  const [isShowSplash, setIsShowSplash] = useState(true);

  useEffect(() => {
    const timeout = setTimeout(() => {
      setIsShowSplash(false);
    }, 1500);

    return () => clearTimeout(timeout);
  }, []);

  return <>{isShowSplash ? <SplashScreen /> : <MainNavigator />}</>;
};

export default AppRouters;
