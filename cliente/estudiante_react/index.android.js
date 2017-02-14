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
  Alert,
  Button,
  Navigator,
  TouchableHighlight,
  Text
} from 'react-native';

import Titulo from './titulo';
import Inicio from './inicio';
import Mapa from './lib/mapa';

let URL = "http://ec2-52-24-181-82.us-west-2.compute.amazonaws.com:3000/data";

var NavigatorBarRouteMapper = {
  LeftButton: function(route, navigator, index){
    if (index > 0){
    return (
      <TouchableHighlight onPress={() => {
        if (index > 0){
          navigator.pop();
        }
        }} >
          <Text style={{marginTop: 10, marginLeft:20, color: '#007AFF'}}>Atras</Text>
        </TouchableHighlight>
    );
    }else{
      return null;
    }
  },
  RigthButton: function(route, navigator, index){
    return null;
  },
  Title: function(route, navigator, index){
    return(
      <Text style={{marginTop:10, color: '#007AFF'}}>
        {route.name}
      </Text>
    );
  }
};


export default class estudiante_react extends Component {

  renderScene(route, navigator){
    switch(route.name){
      case 'Mapa':
        return (
          <Mapa state={this.state} navigator={navigator} route={route}/>
        )
    }
  }

  state = {
    // Ubicacion UNAB
        region:{
            latitude: 7.117127,
            longitude: -73.105399,
            latitudeDelta: 0.015,
            longitudeDelta: 0.0121
        },
        coordinate: {
            latitude:  7.117127,
            longitude: -73.105399
        }
    }
  
  constructor(props){
      super(props);
      
  }

  
  render() {
    return (
      <View style={estilos.container}>
          <Titulo />
          <Navigator 
            style={{backgroundColor: '#fff'}}
            initialRoute={{name: 'Mapa'}}
            renderScene={this.renderScene}
            configureScene={(route, routeStack) => {
              if (route.sceneConfig){
                return route.sceneConfig;
              }
              return Navigator.SceneConfigs.FloatFromRight;
            }}
            navigationBar={
              <Navigator.NavigationBar 
                routeMapper={NavigatorBarRouteMapper} />
            }>
            </Navigator>
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
