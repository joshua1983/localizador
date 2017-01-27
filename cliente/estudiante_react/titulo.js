import React, {Component} from 'react';
import {AppRegistry, StyleSheet, Text, View, Button} from 'react-native';

export default class Titulo extends Component{
    render(){
        return(
        <View  style={estilos.cont_titulo}>
            <Text style={estilos.titulo}>Mapa de ubicación bus UNAB</Text>
            
        </View>
        );
    }
}
const estilos = StyleSheet.create({

  cont_titulo: {
    flex:1
  },
  titulo:{
      fontSize:25
  }
});

