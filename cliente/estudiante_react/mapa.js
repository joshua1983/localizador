import React, {Component} from 'react';
import {
        AppRegistry, 
        Button, 
        View, 
        Alert, 
        StyleSheet,
    } from 'react-native';
import MapView from 'react-native-maps';

const url_server = "";

export default class Mapa extends Component{

    actualizarUbicacion(){
        Alert.alert(ancho);
    }

    render(){
        return(
            <View style={estilos.mapa} >
                <Button
                    title ="Actualizar"
                    onPress= {this.actualizarUbicacion}
                />
                
                    <MapView
                        style={estilos.map}
                        region={{
                        latitude: 37.78825,
                        longitude: -122.4324,
                        latitudeDelta: 0.015,
                        longitudeDelta: 0.0121,
                        }}
                    >
                    </MapView>
                
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
