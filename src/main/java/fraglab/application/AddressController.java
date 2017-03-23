package fraglab.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address find(@PathVariable String id) {
        return addressService.find(id).orElseThrow(RuntimeException::new);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody Address address) {
        addressService.save(address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        addressService.delete(id);
    }

}
