COMPATIBLE_MACHINE:qemu-generic-arm64  = "qemu-generic-arm64"
EDK2_PLATFORM:qemu-generic-arm64 = "SbsaQemu"
EDK2_PLATFORM_DSC:qemu-generic-arm64 = \
    "Platform/Qemu/SbsaQemu/SbsaQemu.dsc"
EDK2_BIN_NAME:qemu-generic-arm64 = "SBSA_FLASH0.fd"


do_compile:prepend:qemu-generic-arm64() {
    mkdir -p ${B}/Platform/Qemu/Sbsa/
    cp ${RECIPE_SYSROOT}/firmware/trusted-firmware-a/bl1.bin \
       ${B}/Platform/Qemu/Sbsa/
    cp ${RECIPE_SYSROOT}/firmware/trusted-firmware-a/fip.bin \
       ${B}/Platform/Qemu/Sbsa/
}

do_install:append:qemu-generic-arm64() {
    install \
        ${B}/Build/${EDK2_PLATFORM}/${EDK2_BUILD_MODE}_${EDK_COMPILER}/FV/SBSA_FLASH*.fd \
        ${D}/firmware/
    truncate -s 256M ${D}/firmware/SBSA_FLASH*.fd
}
DEPENDS:append:qemu-generic-arm64 = \
    " trusted-firmware-a coreutils-native"
MACHINE_TFA_REQUIRE:qemu-generic-arm64 = \
    "trusted-firmware-a-sbsa-ref.inc"

