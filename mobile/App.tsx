import {NavigationContainer} from '@react-navigation/native';
import React from 'react';
import AppRouters from './src/navigators/AppRouters';

const App = () => {
  return (
    <>
      <NavigationContainer>
        <AppRouters />
      </NavigationContainer>
    </>
  );
};

export default App;
