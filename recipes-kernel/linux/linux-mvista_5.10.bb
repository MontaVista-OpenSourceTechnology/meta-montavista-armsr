MV_KERNEL_BRANCH ?= "mvl-5.10/msd.cgx"
MV_KERNEL_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/linux-mvista.git;protocol=https"
MV_KERNELCACHE_BRANCH ?= "yocto-5.10"
MV_KERNELCACHE_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/yocto-kernel-cache;protocol=https"

require recipes-kernel/linux/linux-yocto.inc

SRCREV_machine ?= "${MV_KERNEL_BRANCH}"
SRCREV_meta ?= "${MV_KERNELCACHE_BRANCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

S = "${WORKDIR}/git"

LINUX_VERSION = "5.10"
KERNEL_VERSION_SANITY_SKIP="1"
PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "${MV_KERNEL_TREE};branch=${MV_KERNEL_BRANCH};name=machine \
           ${MV_KERNELCACHE_TREE};type=kmeta;name=meta;branch=${MV_KERNELCACHE_BRANCH};destsuffix=${KMETA} \
	   file://tcpci.cfg"

DEPENDS += "elfutils-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "0"
COMPATIBLE_MACHINE = "null"
COMPATIBLE_MACHINE:generic-arm64 = "generic-arm64"
