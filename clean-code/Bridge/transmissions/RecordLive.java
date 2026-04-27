package br.com.hcode.bridge.transmissions;

import br.com.hcode.bridge.platforms.IPlatform;

public class RecordLive extends Live{
    public RecordLive(IPlatform platform){
        super.platform = platform;
    }

    public void record(){
        System.out.println("Transmissão irá ser gravada");
    }

}
