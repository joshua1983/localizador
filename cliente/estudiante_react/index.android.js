/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  View,
  Button,
  Alert
} from 'react-native';

import Titulo from './titulo';
import Inicio from './inicio';
import Mapa from './lib/mapa';

export default class estudiante_react extends Component {

  constructor(props){
      super(props);
      this.state = {
          latitude: 37.78825,
          longitude: -122.4324,
          latitudeDelta: 0.015,
          longitudeDelta: 0.0121
      }
  }

  _handleResponse(response){
    this.state.latitude = response.lat;
    this.state.longitude = response.lon;

  }

  actualizarUbicacion(){
      fetch('http://ec2-52-24-181-82.us-west-2.compute.amazonaws.com:3000/data')
          .then(response => response.json())
          .then(json => this._handleResponse(json.response))
          .catch(error => {
              Alert.alert("error", error.message);
            });
  }

  render() {
    return (
      <View style={estilos.container}>
          <Titulo />
          <Button
              title ="Actualizar"
              onPress= {this.actualizarUbicacion}
          />
          <Mapa state={this.state} />

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
