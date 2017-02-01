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

    state = {
            latitude: 37.78825,
            longitude: -122.4324,
            latitudeDelta: 0.015,
            longitudeDelta: 0.0121
    }

    _handleResponse(response){
        this.setState({
            latitude: response.lat,
            longitude: response.lon
        });
    }

    actualizarUbicacion(){
        fetch('http://ec2-52-24-181-82.us-west-2.compute.amazonaws.com:3000/data')
            .then(response => response.json())
            .then(json => this._handleResponse(json.response))
            .catch(error => {
                Alert.alert("error", error.message);
              });
    }


    render(){
        return(
            <View >
                <Button
                    title ="Actualizar"
                    onPress= {this.actualizarUbicacion}
                />
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
