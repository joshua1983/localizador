/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Dimensions
} from 'react-native';

import Titulo from './titulo';
import Mapa from './mapa';
var {alto, ancho} = Dimensions.get('window');

export default class estudiante_react extends Component {
  render() {
    return (
      <View style={estilos.container}>
          <Titulo />
          <Mapa alto="alto" />
        
      </View>
    );
  }
}

const estilos = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    paddingTop: 10
  }
});

AppRegistry.registerComponent('estudiante_react', () => estudiante_react);
