import React, {Component} from 'react';
import {
        AppRegistry,
        View,
        Alert,
        StyleSheet,
        Navigator,
        Button
    } from 'react-native';

import {Mapa} from './lib/mapa';


export default class Inicio extends Component{

    render(){
        return(
            <View >
                <Mapa  />
             </View>
        );
    }
}

const estilos = StyleSheet.create({
  mapa:{
    flex: 8,
    ///width: ancho,
    justifyContent:'center',
    ...StyleSheet.absoluteFillObject
  },
  map:{

      height: 400,
      //width: ancho,

  }
});
