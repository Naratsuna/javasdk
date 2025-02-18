package cn.hyperchain.sdk.common.solidity;

import cn.hyperchain.sdk.account.Account;
import cn.hyperchain.sdk.account.Algo;
import cn.hyperchain.sdk.common.utils.ByteUtil;
import cn.hyperchain.sdk.common.utils.FuncParams;
import cn.hyperchain.sdk.exception.RequestException;
import cn.hyperchain.sdk.provider.DefaultHttpProvider;
import cn.hyperchain.sdk.provider.ProviderManager;
import cn.hyperchain.sdk.response.ReceiptResponse;
import cn.hyperchain.sdk.service.AccountService;
import cn.hyperchain.sdk.service.ContractService;
import cn.hyperchain.sdk.service.ServiceManager;
import cn.hyperchain.sdk.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class AbiTest {

    @Test
    public void simpleTest() {
        String contractAbi = "[{"
                + "\"name\":\"simpleFunction\","
                + "\"constant\":true,"
                + "\"payable\":true,"
                + "\"type\":\"function\","
                + "\"inputs\":[{\"name\":\"_in\",\"type\":\"bytes32\"}],"
                + "\"outputs\":[{\"name\":\"_out\",\"type\":\"bytes32\"}]}]";
        System.out.println(contractAbi);
        Abi abi = Abi.fromJson(contractAbi);
        System.out.println(abi.toJson());
        Assert.assertEquals(contractAbi.length(), abi.toJson().length());

        ContractType.Function function = abi.getFunction("simpleFunction(bytes32)");
        FuncParams params = new FuncParams();
        params.addParams("aaa");
        byte[] encode = function.encode(params.getParams());
        System.out.println(ByteUtil.toHex(encode));
    }

    @Test
    public void complexTest() {
        String contractAbi = "[{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"bool\"}],\"name\":\"TestBool\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"bytes\"}],\"name\":\"TestBytes\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"uint8\"}],\"name\":\"TestUint\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"string\"}],\"name\":\"TestString\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"uint256[3]\"}],\"name\":\"TestUintArray\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256[3]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"uint8[3]\"}],\"name\":\"TestUint8Array\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8[3]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"bool[3]\"}],\"name\":\"TestBoolArray\",\"outputs\":[{\"name\":\"\",\"type\":\"bool[3]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"int8[3]\"}],\"name\":\"TestInt8Array\",\"outputs\":[{\"name\":\"\",\"type\":\"int8[3]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address[2]\"}],\"name\":\"TestAddressArray\",\"outputs\":[{\"name\":\"\",\"type\":\"address[2]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"address\"}],\"name\":\"TestAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"int256\"}],\"name\":\"TestInt\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"bytes1\"}],\"name\":\"TestBytes32\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes1\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"constant\":false,\"inputs\":[{\"name\":\"a\",\"type\":\"int256[3]\"}],\"name\":\"TestIntArray\",\"outputs\":[{\"name\":\"\",\"type\":\"int256[3]\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}" +
                ",{\"inputs\":[{\"name\":\"name1\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"a\",\"type\":\"bytes32\"}" +
                ",{\"indexed\":true,\"name\":\"b\",\"type\":\"string\"}],\"name\":\"eventA\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[],\"name\":\"eventB\",\"type\":\"event\"}]";
        Abi abi = Abi.fromJson(contractAbi);
        System.out.println(abi.getConstructor().formatSignature("TypeTestContract"));
    }


    @Test
    public void testFallback() {
        String abiStr = "[{\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"fallback\"}]";
        Abi abi = Abi.fromJson(abiStr);
        System.out.println(abi.toString());
    }
}