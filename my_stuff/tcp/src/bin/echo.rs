use std::{
    io::{Read, Result, Write},
    net::{Shutdown, TcpStream},
};

fn main() -> Result<()> {
    let addr = "127.0.0.1:6969";
    let buffer = vec![b'a'; 1_000_000];

    let mut stream = TcpStream::connect(addr)?;
    let _ = stream.write_all(&buffer);

    stream.shutdown(Shutdown::Write)?;
    let mut total_bytes_read = 0;
    loop {
        let mut read_buf = [0; 4000];
        let bytes_read = stream.read(&mut read_buf)?;
        if bytes_read == 0 {
            break;
        }
        total_bytes_read += bytes_read;
    }

    println!("{}", total_bytes_read);
    Ok(())
}
