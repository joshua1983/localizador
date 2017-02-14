import React, {Component} from 'react';
import {
        AppRegistry,
        View,
        Alert,
        StyleSheet,
        Text,
        Button
    } from 'react-native';
import MapView, {MAP_TYPES, Marker} from 'react-native-maps';

let id=0;
let URL = "http://ec2-52-24-181-82.us-west-2.compute.amazonaws.com:3000/data";
let LATITUDE = 7.117127;
let LONGITUDE = -73.105399;

export default class Mapa extends Component{

    state = {
    // Ubicacion UNAB
        latitude: 7.117127,
        longitude: -73.105399,
        latitudeDelta: 0.015,
        longitudeDelta: 0.0121
    }
    

    constructor(props){
        super(props);
        this.state = this.props.state;
    }


    componenDidMount(){
        getUbicacion();
    }

    actualizarUbicacion(data){
        
        this.state.latitude = data.lat;
        this.state.longitude = data.lon;
        console.log(this.state);
        
    }

    getUbicacion(){

        fetch(URL)
        .then((response) => response.json())
        .then((data) => {
            this.actualizarUbicacion(data);
        })
        .catch((error) => {
            Alert.alert("error", error.message);
        });
    }




    render(){
        
        return(
            <View style={estilos.mapa} >
                    <Text></Text>

                    <MapView
                        style={estilos.map}
                        provider={this.provider}
                        ref={ref => { this.map = ref; }}
                        initialRegion={this.props.state}
                    >
                        <MapView.Marker.Animated coordinate={this.props.state} />
                    
                
                    </MapView>
                    
                    
             </View>
        );
    }
}

Mapa.propTypes = {
  provider: MapView.ProviderPropType,
};

const estilos = StyleSheet.create({
    boton:{
        flex: 1
    },
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
