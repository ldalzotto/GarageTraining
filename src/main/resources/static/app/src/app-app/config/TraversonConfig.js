traverson.registerMediaType(TraversonJsonHalAdapter.mediaType, TraversonJsonHalAdapter);

let baseTraverson = traverson.from('http://localhost:8080/').jsonHal();