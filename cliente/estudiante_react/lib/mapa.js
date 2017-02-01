import React, {Component} from 'react';
import {
        AppRegistry,
        View,
        Alert,
        StyleSheet,
        Text
    } from 'react-native';
import MapView, {MAP_TYPES} from 'react-native-maps';



export default class Mapa extends Component{

    constructor(props){
        super(props);
        this.state = this.props.state;

    }

    render(){
        return(
            <View style={estilos.mapa} >


                    <MapView
                        style={estilos.map}
                        provider={this.props.provider}
                        ref={ref => { this.map = ref; }}
                        initialRegion={this.state}
                        onRegionChange={region => this.onRegionChange(region)}
                    >
                    <MapView.Marker
                      coordinate={{
                        latitude: this.state.latitude,
                        longitude: this.state.longitude,
                      }}
                      centerOffset={{ x: -18, y: -60 }}
                      anchor={{ x: 0.69, y: 1 }}
                    />

                    </MapView>
                    <Text>{this.state.longitude} - {this.state.latitude}</Text>
             </View>
        );
    }
}

Mapa.propTypes = {
  provider: MapView.ProviderPropType,
};

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
