import React from 'react';
import {NotifiScreen} from '../screens';
import {createNativeStackNavigator} from '@react-navigation/native-stack';

const NotifiNavigator = () => {
  const Stack = createNativeStackNavigator();
  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
      }}>
      <Stack.Screen name="NotifiScreen" component={NotifiScreen} />
    </Stack.Navigator>
  );
};

export default NotifiNavigator;
