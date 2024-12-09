use std::{
    io::{Read, Result, Write},
    net::TcpListener,
};

fn main() -> Result<()> {
    let addr = "0.0.0.0:6969";
    let listener = TcpListener::bind(addr)?;

    let (mut stream, _) = listener.accept()?;
    stream.write_all(b"220 Welcome\r\n")?;

    let mut buffer = [0; 4096];
    let mut total_bytes_read = 0;

    loop {
        let bytes_read = stream.read(&mut buffer)?;
        if bytes_read == 0 {
            break;
        }
        total_bytes_read += bytes_read;
    }

    println!("{}", total_bytes_read);
    Ok(())
}
